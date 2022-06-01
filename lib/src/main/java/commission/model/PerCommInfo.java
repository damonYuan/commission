package commission.model;

import java.math.BigDecimal;

public class PerCommInfo extends CommInfo {
    private BigDecimal percentage;
    public PerCommInfo(BigDecimal percentage) {
        super(CalcType.PERCENTAGE);
        this.percentage = percentage;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }
}
