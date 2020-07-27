package com.hito.dataware.scheduler.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "import_last_time")
@Data
public class ImportLastTime implements Serializable {
    @Column(name = "table_name")
    private String tableName;

    @Column(name = "import_time")
    private Long importTime;

    private Integer status;

    @Column(name = "shut_down")
    private Integer shutDown;

    private static final long serialVersionUID = 1L;
}