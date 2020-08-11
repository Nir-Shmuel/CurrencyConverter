public class Currency {
    private String id;
    private Double value;

    public Currency(String id) {
        this.id = id;
        value = 0.0;
    }

    public String getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double newValue) {
        if (newValue != null && !value.equals(newValue))
            this.value = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id.equals(currency.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
