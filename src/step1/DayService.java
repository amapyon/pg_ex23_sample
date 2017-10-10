package step1;

public class DayService {

	private boolean joined = false;

	// サービスに加入しているかをチェック
	public void checkService(Record record) {
		if (record.getServiceCode().equals("E1")) {
			joined = true;
		}

	}

	public int calcBasicCharge(int baseBasicCharge) {
		if (joined) {
			// サービスに加入していれば基本料金を200円増し
			return baseBasicCharge + 200;
		}
		return baseBasicCharge;
	}

	// 単価を計算する
	public int calcUnitPrice(Record record, int baseUnitPrice) {

		if (!joined) {
			// サービスに加入していなければ値引きなし
			return baseUnitPrice;
		}

		int startHour = record.getStartHour(); // 通話開始時間

		if (8 <= startHour && startHour <= 17) {
			// 通話開始時間がサービス対象時間ならば5円引き
			return baseUnitPrice - 5;
		}

		return baseUnitPrice;
	}

	public void clear() {
		joined = false;
	}

}
