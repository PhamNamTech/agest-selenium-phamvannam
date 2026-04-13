package Common;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;

import Constant.Constant;
import Railway.BookTicketData;
import Railway.DataObjectBase;

public class DataProviders {

	@DataProvider
	public <T extends DataObjectBase> Object[][] getBookTicketData(Method method) {
		String testCaseId = method.getName();
		return new JsonDataReader().readJsonAsDataProvider(Constant.BOOK_TICKET_DATA_JSON, testCaseId,
				new TypeReference<List<BookTicketData>>() {
				});
	}
}
