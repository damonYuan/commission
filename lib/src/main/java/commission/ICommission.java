package commission;

import java.math.BigDecimal;
import java.util.List;

public interface ICommission {
    BigDecimal getGrossCommission();
    BigDecimal getMarketFees();
    BigDecimal getMarketFeeByType(MarketFeeType type);
    List<MarketFeeType> getIncludedMarketFees();
    BigDecimal getNetCommission();
}
