package commission.service;

import commission.model.CommInfo;
import commission.model.IFee;
import commission.model.FeeType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public interface IComm {
    Integer getSize();
    BigDecimal getGrossPrice();
    BigDecimal getGrossAmount(); // size * grossPrice
    Integer getScale();
    RoundingMode getRoundingMode();
    IFee getFeeByType(FeeType type);
    List<IFee> getFees();

    BigDecimal getGrossComm();

}
