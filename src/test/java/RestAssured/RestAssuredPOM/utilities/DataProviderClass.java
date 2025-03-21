package RestAssured.RestAssuredPOM.utilities;
import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviderClass {

    private static final String FILE_PATH = "C:/Users/dasar/eclipse-workspace/RestAssuredPOM/TestData/Book1.xlsx";
    private static final String SHEET_NAME = "Sheet1";

    @DataProvider(name = "allData")
    public static Object[][] provideAllData() throws IOException {
        ExcelUtility excel = new ExcelUtility(FILE_PATH, SHEET_NAME);
        Object[][] data = excel.getAllData();
        excel.closeWorkbook();
        return data;
    }

    @DataProvider(name = "userNames")
    public static Object[][] provideUserNames() throws IOException {
        ExcelUtility excel = new ExcelUtility(FILE_PATH, SHEET_NAME);
        Object[][] usernames = excel.getUserNames();
        excel.closeWorkbook();
        return usernames;
    }
}
