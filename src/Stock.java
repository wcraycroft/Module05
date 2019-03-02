import java.text.NumberFormat;
import java.util.Objects;

public class Stock {

    private String mCompanyName;    // The company's name (e.g. Apple)
    private String mStockSymbol;    // The company's stock symbol (e.g APPL)
    private double mStockPrice;     // The company's stock price in USD

    // Parameterized constructor - takes in company name, stock symbol and stock price
    public Stock(String companyName, String stockSymbol, double stockPrice) {
        mCompanyName = companyName;
        mStockSymbol = stockSymbol;
        mStockPrice = stockPrice;
    }

    // Getters and Setters
    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getStockSymbol() {
        return mStockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        mStockSymbol = stockSymbol;
    }

    public double getStockPrice() {
        return mStockPrice;
    }

    public void setStockPrice(double stockPrice) {
        mStockPrice = stockPrice;
    }

    // equals method - checks if passed object is of Stock type and compares company name,
    // stock symbol and stock price.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.mStockPrice, mStockPrice) == 0 &&
                Objects.equals(mCompanyName, stock.mCompanyName) &&
                Objects.equals(mStockSymbol, stock.mStockSymbol);
    }

    // toString - returns String representation of Stock
    // e.g. "Stock{CompanyName=Apple, StockSymbol=APPL, StockPrice=$120.52}"
    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        return "Stock{" +
                "CompanyName='" + mCompanyName + '\'' +
                ", StockSymbol='" + mStockSymbol + '\'' +
                ", StockPrice=" + currency.format(mStockPrice) +
                '}';
    }
}
