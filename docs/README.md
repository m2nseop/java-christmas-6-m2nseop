## 목표
1. 객체 분리 더 연습
2. InputView, OutputView 클래스 구현


## 기능명세서

- [x] 방문날짜 입력받기 - readVisitDate() | InputView
  - [x] (예외처리) 1~31의 숫자여야한다. - isValidDate() | OrderMenuValidator
  - [x] (예외처리) 숫자여야한다. - isNumber() | InputView
  - [x] (예외처리) 공백, null이 아니여야한다. - isEmpty() | InputView
- [x] 주문 메뉴와 개수 입력받기 - InputView | readMenuOrder
  - [x] (예외처리) 공백, null이 아니여야한다. - isEmpty() | InputView
  - [x] (예외처리) 주문 입력형식이 다른 경우 - checkValidOrderForm() | OrderMenuValidator
  // 주문메뉴-주문수량 or 주문메뉴-주문수량,주문메뉴-주문수량 형식까지 검증 완료
  - [x] (예외처리) 중복 메뉴를 입력한 경우 - checkDuplicateMenu() | OrderMenuValidator
  - [x] (예외처리) 주문 메뉴 최대 개수는 20개 까지 가능하다. - checkMaxOrderQuantity() | OrderMenuValidator
  - [x] (예외처리) 메뉴판에 없는 메뉴를 주문할 경우 - checkExistingMenu() | OrderMenuValidator
  - [x] (예외처리) 음료수만 시킬 수는 없다 - checkMenuContainsOnlyDrink() | OrderMenuValidator
- [x] 주문 메뉴 출력 (순서는 자유 -> 입력받은 순으로 출력하면 될 듯) - printOrderedMenu() | OutputView
- [x] 할인 전 총주문 금액 계산 - calculatePreDiscountTotalOrderPrice() | EventPlanner 
- [x] 할인 전 총주문 금액 출력 - printPreDiscountTotalOrderPrice() | OutputView
- [x] 증정 메뉴 결정 - checkGiftMenu() | EventBenefitSettler
- [x] 증정 메뉴 출력 - printGiftMenu() | OutputView
  - [x] (예외처리) 증정할 메뉴가 없으면 "없음"으로 출력한다.
- [x] 적용된 혜택 내역 계산 - calculateReceivedBenefits()
- [x] 적용된 혜택 내역 출력 (혜택 종류: 할인된 금액) - printReceivedBenefits()
  - [x] (예외처리) 적용된 이벤트가 하나도 없다면 "없음"으로 출력한다.
  - [x] (예외처리) 주문 금액이 10000원 아래일 경우 혜택 적용 x
- [x] 총혜택 금액 계산 - caculateBenefitsTotalAmount() | EventBenefitSettler
- [x] 총혜택 금액 출력 (할인 금액의 합계 + 증정 메뉴의 가격) - printBenefitsTotalAmount() | OutputView
- [x] 할인 후 예상 결제 금액 계산 - calculateDiscountedTotalAmount() | EventBenefitSettler
- [x] 할인 후 예상 결제 금액 출력 (할인전 총 주문 금액 - 할인 금액) - printDiscountedTotalAmount() | OutputView
- [x] 이벤트 배지 종류를 결정 - findMyEventBadyType() | EventBadge
- [x] 이벤트 배지 출력 - printEventBadgeType() | OutputView
  - [x] (예외처리) 없으면 "없음" 출력

## 객체

- [x] Menu 객체
  - [x] menuName
  - [x] menuPrice
  - [x] menuCategory
  - [x] menuQuantity

[x] (예외처리) 존재하지 않는 메뉴 이름인 경우
[x] (예외처리) 주문 메뉴 개수가 숫자가 아닌 경우
[x] (예외처리) 주문 메뉴 개수가 0인경우

- [x] EventPlanner 객체
  - [x] 메뉴 리스트

- [x] EventBenefitSettler
  - [x] visitDate


## enum

- [x] EventBadge (이벤트 배지 관리)
  총 "혜택" 금액 에따라 배지를 부여한다.
  5천원 이상: 별
  1만원 이상: 트리
  2만원 이상: 산타

- [x] EventBenefit (이벤트 혜택 관리)
  크리스마스 할인
  평일할인
  주말할인
  특별할인
  증정선물

- [x] SpecialDiscountDay (특별 할인 날짜 관리)

## 상수

- [x] EventOption (이벤트 관련 설정값 관리)



1. 크리스마스 데데이 할인 (2023.12.1 ~ 2023.12.25)
12월 1일부터
"총 주문금액"에서 할인 금액이 1,000원으로 시작하여 크리스마스까지 날마다 할인 금액이 100원씩 증가
12월 1일 -> 1000원 할인
12월 2일 -> 1100원 할인
12월 25일 -> 3400원 할인

2. "평일" 할인 - isWeekday()
조건: 일, 월, 화, 수, 목
혜택 품목: 디저트 메뉴
할인 금액: 메뉴 1개당 2,023원

3. "주말" 할인 - isWeekend()
조건: 금, 토
혜택 품목: 메인 메뉴
할인 금액: 메뉴 1개당 2,023원

4. 특별 할인 - isStarday()
조건: 이벤트 달력에 별이 있는 요일
12월달의 모든 일요일, 12월 25일
혜택 품목: 총 주문금액
할인 금액: 1,000원

5. 증정 이벤트
조건: 할인 전 총주문금액이 12만원 이상인 경우
혜택 품목: 삼폐인 1개 증정

6. 이벤트 기간
2,3,4,5 -> 12월 1일 ~ 12월 31일

(예외처리)
- 총주문 금액 10,000원 이상부터 이벤트가 적용된다.
- 음료만 주문 시, 주문할 수 없다.
- 메뉴는 한 번에 최대 20개까지만 주문할 수 있다.

--------------------------










