class Currency {
    private final String name;
    private final String sign;
    private final String code;

    public Currency(String name, String sign, String code) {
        this.name = name.trim().toLowerCase();
        this.sign = sign.trim();
        this.code = code.trim().toUpperCase();
    }

    public Currency(String name, String code) {
        this.name = name.trim().toLowerCase();
        this.code = code.trim().toUpperCase();
        this.sign = this.code;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public String getCode() {
        return code;
    }
}
