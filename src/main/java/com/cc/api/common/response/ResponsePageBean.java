package com.cc.api.common.response;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public class ResponsePageBean<T> extends ResponseBean<T> {
    private Integer totalCount;

    public ResponsePageBean(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public ResponsePageBean(ResponseStatus status, Integer totalCount) {
        super(status);
        this.totalCount = totalCount;
    }

    public ResponsePageBean(ResponseStatus status, T data, Integer totalCount) {
        super(status, data);
        this.totalCount = totalCount;
    }

    public ResponsePageBean(ResponseStatus status, String msg, Integer totalCount) {
        super(status, msg);
        this.totalCount = totalCount;
    }

    public ResponsePageBean(ResponseStatus status, String msg, T data, Integer totalCount) {
        super(status, msg, data);
        this.totalCount = totalCount;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
