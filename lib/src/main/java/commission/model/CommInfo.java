package commission.model;

public class CommInfo {
    private CalcType type;
    public CommInfo(CalcType type) {
        this.type = type;
    }

    public CalcType getType() {
        return this.type;
    }
}
