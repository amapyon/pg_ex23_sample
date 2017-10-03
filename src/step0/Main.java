package step0;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/invoice.dat"));
		RecordReader reader = new RecordReader();

		String ownerTelNumber = null;
		int basicCharge = 1000; // 基本料金
		int unitPrice = 20; // 通話単価
		int callCharge = 0; // 通話料金

		for (Record record = reader.read(); record != null; record = reader.read()) {

			char recordCode = record.getRecordCode();

			switch (recordCode) {
			case '1':
				ownerTelNumber = record.getOwnerTelNumber();
				break;

			case '5':
				int callTime = record.getCallMinutes();
				callCharge += unitPrice * callTime;
				break;

			case '9':
				writer.write("1 " + ownerTelNumber + "\n");
				writer.write("5 " + basicCharge + "\n");
				writer.write("7 " + callCharge + "\n");
				writer.write("9 ====================\n");

				callCharge = 0;
				break;
			}

		}
		reader.close();
		writer.close();
	}

}
