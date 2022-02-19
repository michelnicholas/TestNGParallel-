import org.testng.annotations.DataProvider;

public class TestData {

    // using data provider to parse in data thru two array objects
    @DataProvider(name = "calc-data")
    Object[][] testData() {
        return new Object[][]{
                {"2 + 3", "5"}, // data set
                {"sqrt 16", "4"},// data set
                {"3 - 2", "1"} // data set
        };
    }


}
