package model;

public class Meta {

    private int code;

    public Meta() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "code=" + code +
                '}';
    }
}
