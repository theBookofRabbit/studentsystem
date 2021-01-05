package cn.xiao.studentsystem.Utils;

public enum ServiceExceptionEnum {

    // ========== 系统级别 ==========
    SUCCESS(1, "成功"),
    MANAGER_NUM_OR_PASSWORD_WORING(-1,"管理员工号或密码错误"),
    STUDENT_MESSAGE_NOT_COMPLIE(-1,"学生必填信息不完整"),
    STUDENT_NUM_NOT_EXIST(-1,"学生学号不存在"),
    RESULT_NOT_COMPLIE(-1,"学号/成绩/课程号不能为空")

    ;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误提示
     */
    private String message;

    ServiceExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}