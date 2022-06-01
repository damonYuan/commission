package commission.service;

import commission.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Commission implements IComm {
    final private Integer scale;
    final private RoundingMode roundingMode;
    final private Integer size;
    final private BigDecimal grossPrice;
    private List<IFee> fees;

    private Commission(
            Integer scale,
            RoundingMode roundingMode,
            Integer size,
            BigDecimal grossPrice,
            List<IFee> fees
    ) {
        this.scale = scale;
        this.roundingMode = roundingMode;
        this.size = size;
        this.grossPrice = new BigDecimal(grossPrice.unscaledValue(), grossPrice.scale());
        this.fees = fees;
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public BigDecimal getGrossPrice() {
        return new BigDecimal(this.grossPrice.unscaledValue(), this.grossPrice.scale());
    }

    @Override
    public BigDecimal getGrossAmount() {
        return this.grossPrice.multiply(new BigDecimal(this.size)).setScale(this.scale, this.roundingMode);
    }

    @Override
    public Integer getScale() {
        return this.scale;
    }

    @Override
    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    @Override
    public IFee getFeeByType(FeeType type) {
        return this.fees.stream().filter(f -> f.getFeeType() == type).findFirst().orElse(null);
    }

    @Override
    public List<IFee> getFees() {
        // return all fees
        return null;
    }

    @Override
    public BigDecimal getGrossComm() {
        // add up all fees including commission, stamp duty, soft dollar etc.
        return null;
    }


    public static class Builder {
        private Integer scale;
        private RoundingMode roundingMode;
        private Integer size;
        private BigDecimal grossPrice;
        private final List<IFee> fees;

        public Builder() {
            this.scale = 8;
            this.roundingMode = RoundingMode.HALF_UP;
            this.size = 0;
            this.grossPrice = BigDecimal.ZERO;
            this.fees = new ArrayList<>();
        }

        public Builder setScale(Integer scale) {
            this.scale = scale;
            return this;
        }

        public Builder setRoundingMode(RoundingMode roundingMode) {
            this.roundingMode = roundingMode;
            return this;
        }

        public Builder setSize(Integer size) {
            this.size = size;
            return this;
        }

        public Builder setGrossPrice(BigDecimal grossPrice) {
            this.grossPrice = grossPrice;
            return this;
        }

        public Builder setFee(IFee fee) {
            List<IFee> tmp = this.fees.stream().filter(f -> f.getFeeType() == fee.getFeeType()).collect(Collectors.toList());
            if (tmp.size() == 0) {
                this.fees.add(fee);
            } else {
                this.fees.removeAll(tmp);
                this.fees.add(fee);
            }
            return this;
        }

        public Commission build() {
            return new Commission(
                    this.scale,
                    this.roundingMode,
                    this.size,
                    this.grossPrice,
                    this.fees);
        }
    }
}
