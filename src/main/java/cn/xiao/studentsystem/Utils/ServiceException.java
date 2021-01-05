package cn.xiao.studentsystem.Utils;

public final class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    private Object data;

    public ServiceException(Object data, ServiceExceptionEnum serviceExceptionEnum) {
        // 使用父类的 message 字段
        super(serviceExceptionEnum.getMessage());
        // 设置错误码
        this.code = serviceExceptionEnum.getCode();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}