package step2;

public class FamilyService {
	private static final int TEL_NUMBER = 2;

	// 料金計算のための基礎情報
	private static final String SERVICE_CODE = "C1";
	private static final int BASIC_CHARGE = 100;

	// 家族割引
	private boolean joined = false;
	private String[] telNumbers = new String[TEL_NUMBER];
	private int telNumberCount = 0;

	// 変数を初期化する
	public void clear() {
		joined = false;
		telNumberCount = 0;
	}

	// 家族割引対象電話番号を追加する
	public void appendFamilyTelNumber(String telNumber) {
		joined = true;
		telNumbers[telNumberCount++] = telNumber;
	}

	// 家族割引に加入しているか
	public boolean isJoined() {
		return joined;
	}

	// 家族割引対象電話番号かどうかを判定する
	public boolean isFamilyTelNumber(String telNumber) {
		for (int i = 0; i < telNumberCount; i++){
			if (telNumbers[i].equals(telNumber)) {
				return true;
			}
		}
		return false;
	}

	// 割引サービスに加入しているかを検査する
	public void checkService(Record record) {
		if (SERVICE_CODE.equals(record.getServiceCode())) {
			// 家族割引 登録されている電話番号を一時保管
			appendFamilyTelNumber(record.getServiceOption());
		}
	}

	// 単価を計算する
	public int calcUnitPrice(Record record, int unitPrice) {
		if (isFamilyTelNumber(record.getCallNumber())) {
			// 家族割引なら半額
			unitPrice /= 2;
		}
		return unitPrice;
	}

	// 基本料金を計算する
	public int calcBasicCharge(int basicCharge) {
		if (isJoined()) {
			basicCharge += BASIC_CHARGE;
		}
		return basicCharge;
	}
}
