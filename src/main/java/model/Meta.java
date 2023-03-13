package model;

public class Meta {
    private int code;
    private String disclaimer;

    public Meta() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "code=" + code +
                ", disclaimer='" + disclaimer + '\'' +
                '}';
    }
}
