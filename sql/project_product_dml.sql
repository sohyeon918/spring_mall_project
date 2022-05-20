-- 상품 전체 리스트
--- 페이징을 활용한 전체 회원정보 조회 (인라인 뷰(inline-view) 활용)
--- 한번에 5건씩 출력
--- 아이디 중심으로 오름차순 정렬
 
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT * FROM product
             ORDER BY pseq ASC
           ) m  
      )  
WHERE page = 1;


-- 총 상품수
SELECT count(*) FROM product

-- 상품 상태 수정

-- 상품 사용 여부 수정
UPDATE product SET useyn='y' WHERE pseq=1;

-- 베스트 상품 여부 수정
UPDATE product SET bestyn='y' WHERE pseq=1;

-- 상품 검색

-- 상품 분류(O) + 검색어(O)
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT * FROM product 
			 WHERE kind = 1 AND name like '%힐%'
			 ORDER BY pseq ASC
           ) m  
      )  
WHERE page = 1;

--------------------------------------

-- 상품 분류(X:전체) + 검색어(O)
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT * FROM product 
			 WHERE name like '%스니커즈%'
			 ORDER BY pseq ASC
           ) m  
      )  
WHERE page = 1;

--------------------------------------

-- 상품 분류(O) + 검색어(X)
SELECT *  
FROM (SELECT ROWNUM,  
             m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT * FROM product 
			 WHERE kind = 1
			 ORDER BY pseq ASC
           ) m  
      )  
WHERE page = 1;

------------------------------------------------

-- 상품 분류(O) + 검색어(O)
WHERE kind = 1 AND name like '%힐%'

-- 상품 분류(X) + 검색어(O)
WHERE name like '%스니커즈%'

-- 상품 분류(O) + 검색어(X)
WHERE kind = 1

---------------------------------------------------

-- 검색 (페이징) 총 상품수

-- 상품 분류(O) + 검색어(O)
SELECT count(*)
FROM product 
WHERE kind = 1 AND name like '%힐%'
ORDER BY pseq ASC;

-- 상품 분류(X:전체) + 검색어(O)
SELECT count(*)
FROM product 
WHERE name like '%스니커즈%'
ORDER BY pseq ASC;

-- 상품 분류(O) + 검색어(X)
SELECT count(*)
FROM product 
WHERE kind = 1
ORDER BY pseq ASC;

---------------------------------------------------

-- 카트에 상품 담기
INSERT INTO cart(cseq, id, pseq, quantity)
VALUES (cart_seq.nextval,?, ?, ?);

-- 개별 카트 조회
SELECT * FROM cart
WHERE id='spring1234' and pseq=610;

-- 카트 수정
UPDATE cart SET
quantity = ?,
result = ?,
indate = ?
WHERE cseq = 9;

-- 카트 삭제
DELETE cart WHERE cseq=?;

-- 최근 주문 내역의 기본키(최근 주문 번호)
SELECT max(oseq) FROM orders;

-- 주문 내역 추가
INSERT INTO orders(oseq, id) 
VALUES (orders_seq.nextval, ?);

-- 주문 상세 내역 추가
INSERT INTO order_detail(odseq, oseq, pseq, quantity) 
VALUES(order_detail_seq.nextval, ?, ?, ?);

-- 주문 현황 조회
SELECT * FROM order_view 
WHERE id=? 
and result like '%'||?||'%' 
and oseq=?

-- 진행중인 주문 조회
SELECT DISTINCT oseq FROM order_view 
WHERE id=? AND result='1' 
ORDER BY oseq desc;

-- 관리자 : 주문 현황 조회
SELECT * FROM order_view 
WHERE mname LIKE '%'||?||'%'
ORDER BY result, oseq DESC;

-- 관리자 : 주문 처리 상태 수정(갱신)
UPDATE order_detail SET result='2' WHERE odseq=?