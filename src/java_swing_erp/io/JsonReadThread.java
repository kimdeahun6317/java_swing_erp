package java_swing_erp.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java_swing_erp.dto.Student;

public class JsonReadThread extends Thread{
	private Gson gson = new Gson();
	private String filePath;
	private List<Student> list = new ArrayList<Student>();

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			list =  gson.fromJson(line, new TypeToken<List<Student>>() {}.getType());
		} catch (FileNotFoundException e) {
			System.err.println("해당 파일이 존재하지 않음");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Student> getList() {
		return list;
	}

	public void setFileName(String filePath) {
		this.filePath = filePath;
	}

}
