package commission.model;

import java.math.BigDecimal;

public class UnitCommInfo extends CommInfo {
    private BigDecimal unitPrice;
    public UnitCommInfo(BigDecimal unitPrice) {
        super(CalcType.FIXED_PER_UNIT);
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }
}
