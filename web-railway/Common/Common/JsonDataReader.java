package Common;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Railway.DataObjectBase;

public class JsonDataReader {

	private final ObjectMapper mapper = new ObjectMapper();

	public <T extends DataObjectBase> Object[][] readJsonAsDataProvider(String resourcePath, String testCaseId,
			TypeReference<List<T>> typeRef) {

		List<T> dataList = loadListFromJsonResource(resourcePath, typeRef);

		List<T> filtered = dataList.stream().filter(data -> testCaseId.equals(data.getTestCaseId()))
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			throw new RuntimeException("No data found for test case id: " + testCaseId);
		}

		return convertToDataProvider(filtered);
	}

	private <T> List<T> loadListFromJsonResource(String resourcePath, TypeReference<List<T>> typeRef) {

		try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(resourcePath)) {

			if (inputStream == null) {
				throw new RuntimeException("Cannot find JSON resource: " + resourcePath);
			}

			return mapper.readValue(inputStream, typeRef);

		} catch (Exception e) {
			throw new RuntimeException("Failed to read JSON file: " + resourcePath, e);
		}
	}

	private <T> Object[][] convertToDataProvider(List<T> dataList) {

		Object[][] result = new Object[dataList.size()][1];

		for (int i = 0; i < dataList.size(); i++) {
			result[i][0] = dataList.get(i);
		}

		return result;
	}
}
