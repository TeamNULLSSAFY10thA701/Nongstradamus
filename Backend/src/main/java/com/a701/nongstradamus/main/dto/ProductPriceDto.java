package com.a701.nongstradamus.main.dto;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDto {
    private String name;
    private String nickname;
    private String unit;
    private Integer mention;
    private Long[] prices;
    public void setMention(boolean b) {
        Double average = Arrays.stream(prices).mapToDouble(price -> (double) price).average()
            .getAsDouble();
        double[] avgPrices = new double[12];
        for (int i = 1; i < 13; i++) {
            avgPrices[i - 1] = (prices[i - 1] + prices[i] + prices[i + 1]) / 3.0;
        }
        int[] trends;

        trends = new int[10];
        for (int i = 1; i < 11; i++) {
            double avg = (avgPrices[i - 1] + avgPrices[i] + avgPrices[i + 1]) / 3.0;
            if (avg <= average * 0.9) {
                trends[i - 1]--;
            } else if (avg >= average * 1.1) {
                trends[i - 1]++;
            }
            double ratio = (avgPrices[i] - avgPrices[i-1]) / (double) avgPrices[i-1];
            if(ratio <= - 0.1){
                trends[i-1] -= 2;
            }else if(ratio >= 0.1){
                trends[i-1] += 2;
            }
        }
        for(int size = 8; size >= 4; size -= 2){
            int[] tempTrend = trends;
            trends = new int[size];
            for(int i=1; i < size + 1; i++){
                trends[i-1] = tempTrend[i-1] + tempTrend[i] + tempTrend[i+1];
                trends[i-1] = trends[i-1] == 0 ? 0 : trends[i-1] < 0 ? -1 : 1;
            }
        }
        int past = trends[0] + trends[1];
        int future = trends[1] + trends[2];
        if(b){
            if(past < 0 && future > 0){
                this.mention = 0;
            }else if(past < 0 && future <= 0){
                this.mention = 1;
            }else if(past == 0 && future > 0){
                this.mention = 2;
            }else{
                this.mention = 3;
            }
        }else{
            if(past < 0 && future < 0){
                this.mention = 0;
            }else if(past >=0 && future < 0){
                this.mention = 1;
            }else if(past == 0 && future < 0){
                this.mention = 2;
            }else{
                this.mention = 3;
            }
        }
    }
}
