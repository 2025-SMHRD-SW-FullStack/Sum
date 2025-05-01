package Cash;

import java.util.ArrayList;
import java.util.Random;

public class PlayMethod extends Thread{
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
	
	public int run(int level) { // 타이머 - 조건 수정
		super.run();
		int levelTime = 0;

		int time =0;
		if (level == 1) {
			levelTime = 20;
		} else if (level == 2) {
			levelTime = 15;
		} else if (levelTime == 3) {
			levelTime = 30;
		}
		
		for (int i = 0; i < levelTime; i++) {
			try {
				// if(정답이 아닐때) 
				// else if(정답일때) 멈춰서 time 변수에 i값 넣어서 return
				Thread.sleep(1000);
				System.out.println(levelTime - i);
				// else if 안에 들어감 time = i; 정답을 맞췄을때1 i 값 넣기 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return time;

	}

}
