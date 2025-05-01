package Cash;

import java.util.ArrayList;
import java.util.Random;

public class PlayMethod {
	Random rd = new Random();

	private int fprice = 0; // 최종 금액
	private int refund = 0; // 환불금

	public ArrayList<ObjectVO> object(int level) {
		// 개수 변경을 위해 ArrayList에 적음
		ArrayList<ObjectVO> objectList = new ArrayList<ObjectVO>();

		int radishNum = rd.nextInt(10) + 1; // 변경되는 무 값 5-> 무 개당 가격: 500,550,525
		int radishCount; // 무 개수
		int radishPrice = radishNum * 100; // 무 개당 가격

		int objectCount = 5;
		if (level == 2) { // Normal
			objectCount = 10;
			radishPrice = radishNum * 110;
		} else if (level == 3) { // Hard
			objectCount = 20;
			radishPrice = radishNum * 115;
		}

		radishCount = rd.nextInt(objectCount) + 1;

		// list에서 가격이랑 개수를 빼와서 main에서 곱해야함!!!!!!!!!!!!!!!
		objectList.add(new ObjectVO("가구", 10000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("생선", 5000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("곤충", 7000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("과일", 6000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("꽃", 3000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("무", radishPrice, radishCount));

		return objectList;
	}

	public int refundRandom(int level) {
		// 물품 환불 확률 (부분환불/전체환불/환불x)
		ArrayList<ObjectVO> objectList = new ArrayList<ObjectVO>();

		objectList = object(level);

		int refundNum = rd.nextInt(100) + 1; // 환불 받을 확률
		int index = rd.nextInt(objectList.size());

		if (refundNum <= 20) { // 부분 환불
			refund /= 2;
			if (refundNum <= 5) {
				refund = objectList.get(index).getObjectSel();
			}
		} else
			refund = 0;

		return refund;
		// 최종값에서 환불금 뺀 금액이 시재에 저장되어야하기에 환불금을 리턴 해줌
	}
	
	
	
	   public void gameLevel(RankDTO dto, String userId, int gmLevel) {

		      if (dto.getGmLevel() == 1) {

		      } else if (dto.getGmLevel() == 2 && dto.getUserId().equals(userId) && dto.getGmClear().equals("Y")) {
		         
		         } else if (dto.getGmLevel() == 3 && dto.getUserId().equals(userId) && dto.getGmClear().equals("Y")) {
		            
		      }
		   }

}
