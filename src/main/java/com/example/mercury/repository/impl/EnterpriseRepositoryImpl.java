package com.example.mercury.repository.impl;

import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.entitiy.User;
import com.example.mercury.formDTO.EnterpriseDocumentNumer;
import com.example.mercury.repository.EnterpriseRepository;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Repository
public class EnterpriseRepositoryImpl implements EnterpriseRepository {
    //Если надо будет уйти на сессии то entityManager.unwrap(Session.class);
    //https://stackoverflow.com/questions/5640778/hibernate-sessionfactory-vs-jpa-entitymanagerfactory
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void add(Enterprise enterprise) {
        entityManager.persist(enterprise);
    }

    @Override
    @Transactional
    public void update(Enterprise enterprise) {
        entityManager.merge(enterprise);
    }

    @Override
    public Enterprise getByUUID(UUID uuid) {
        return entityManager.find(Enterprise.class,uuid);
    }

    @Override
    public List<Enterprise> getAll() {
        return entityManager.createQuery("select enterprise from Enterprise as enterprise").getResultList();
    }

    @Override
    public List<Enterprise> getAllByUser(User user) {
        return entityManager.createQuery("Select enterprise From Enterprise as enterprise where enterprise.user=:User").setParameter("User",user).getResultList();

    }

    @Override
    public List<EnterpriseDocumentNumer> getListNumerDocumentOfEnterpriseByUser(User user) {

    /*    ResultTransformer resultTransformer = new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] tuple, String[] aliases ) {
                return new EnterpriseDocumentNumer(
                        (Enterprise)tuple[0],
                        ((Long)tuple[1]).intValue());
            }

            @Override
            public List transformList(List tuples) {
                return tuples;
            }
        };

        List<EnterpriseDocumentNumer> list = (List<EnterpriseDocumentNumer>) entityManager.createQuery("select enterprise,count(document) " +
                        "From Enterprise as enterprise " +
                        "left join Document as document on document.enterprise=enterprise " +
                        "where enterprise.user=:User and (document.processed=false or document.processed is null ) group by enterprise").setParameter("User",user)
                .unwrap(org.hibernate.query.Query.class).setResultTransformer(resultTransformer).getResultList();

        Collections.sort(list, Comparator.comparing(o -> o.getEnterprise().getName()));
        return list;*/

        Function docsCountProcessPrepare = o -> {
            Object[] ob = (Object[]) o;
            return(new EnterpriseDocumentNumer((Enterprise)ob[0], (ob[1] == null || (Boolean) ob[1] == true) ? 0 : 1));
        };

        Map<Enterprise,Integer> docsMap = (Map<Enterprise,Integer>)entityManager.createQuery("select enterprise, document.processed From Enterprise as enterprise " +
                        "left join Document as document on document.enterprise=enterprise where enterprise.user=:User").setParameter("User", user)
                .getResultStream().map(docsCountProcessPrepare)
                .collect(Collectors.toMap(o -> ((EnterpriseDocumentNumer) o).getEnterprise(), o -> ((EnterpriseDocumentNumer) o).getDocumentCounter(), (val1, val2) -> val1 + val2));


        List<EnterpriseDocumentNumer> list = docsMap.entrySet().stream()
                .map(o->new EnterpriseDocumentNumer(o.getKey(), o.getValue()))
                .collect(Collectors.toList());

        Collections.sort(list, Comparator.comparing(o -> o.getEnterprise().getName()));

        return list;
    }

    @Override
    public int getCount() {
        return ((Long)entityManager.createQuery("SELECT count(enterprise) from Enterprise as enterprise").getSingleResult()).intValue();
    }
}
