package com.example.mercury.entitiy;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
public class Document {
    @Id
    UUID uuid;
    //TODO расширить статусы. Возможно: Добавлен, Гашение, Погашен, Ошибка
    //@Column
    //ENUM status;
    @Column
    Boolean processed;
    @Column
    Date date;
    @Column
    String content;
    @ManyToOne
    @JoinColumn(name="enterprise_uuid")
    Enterprise enterprise;

    public Document() {}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}


