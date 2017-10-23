package step2;

public class DayService {
	private static final int START_TIME = 8;
	private static final int END_TIME = 17;

	private static final String SERVICE_CODE = "E1";

	// 割引サービスに加入しているか
	private boolean joined = false;

	//変数を初期化する
	public void clear() {
		joined = false;
	}

	// 割引サービスに加入済み
	public void joined() {
		joined = true;
	}

	// 割引サービスに加入しているか
	public boolean isJoined() {
		return joined;
	}

	// サービスに加入しているかをチェック
	public void checkService(Record record) {
		if (SERVICE_CODE.equals(record.getServiceCode())) {
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

	// 昼トク割引対象時間かどうかを判定する
	public boolean isServiceTime(int hour) {
		if (isJoined()) {
			if((hour >= START_TIME) && (hour <= END_TIME)){
				return true;
			}
		}
		return false;
	}

	// 単価を計算する
	public int calcUnitPrice(Record record, int baseUnitPrice) {

		if (!joined) {
			// サービスに加入していなければ値引きなし
			return baseUnitPrice;
		}

		int startHour = record.getStartHour(); // 通話開始時間

		if (isServiceTime(startHour)) {
			// 通話開始時間がサービス対象時間ならば5円引き
			return baseUnitPrice - 5;
		}

		return baseUnitPrice;
	}


}
