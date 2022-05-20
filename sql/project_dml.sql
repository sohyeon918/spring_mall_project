-- 베스트 상품 조회
select *  from best_pro_view;

-- 신상품 조회
select *  from new_pro_view;

-- 카테고리=1 상품들 조회
select * from product where kind=5;

-- 상품 상세 조회
select * from product where pseq=21;

-- 회원 정보 삽입
insert into member values 
('abcd1234', '#Abcd1234', '홍길동', 'abcd@abcd.com', '010-1234-5678',
'07714','서울특별시 강서구 화곡로 149(화곡동)', '심당빌딩 404호', 'y', sysdate);

insert into member values 
('java1234', '#Abcd5678', '장길산', 'java@abcd.com', '010-1111-2222',
'07714','서울특별시 강서구 화곡로 149(화곡동)', '심당빌딩 404호', 'y', sysdate);

-- 개별 회원 정보 조회 : 아이디 존재 여부
select count(*) from member where id='abcd1234';

-- 이메일 중복 점검
select count(*) from member where email='abcd@abcd.com';

-- 연락처 중복 점검
select count(*) from member where phone='010-1234-5678';

-- 로그인
select count(*) from member where id='abcd1234' and pwd='#Abcd1234';

-- 개별 회원 정보 조회
select * from member where id='abcd1234';

-- 개별 회원 정보 수정(갱신)
UPDATE member SET
pwd='@Abcd12345',
email='abcdabcdabcd@naver.com',
phone='010-9898-7878',
zip_num='12345',
address1='서울시 강서구 화곡동',
address2='12345678',
useyn='y'
WHERE id='abcd1234';

-- 이메일 중복 점검(회원 정보 수정) : 기존의 자신의 이메일을 배제한 나머지 이메일과 중복 점검
select count(*) from (
	select email from member 
	where id != 'abcd1234'
) 
where email='javajava@abcd.com';

-- 자신의 이메일 외의 이메일 현황
select email from member 
where id != 'abcd1234';

-- 연락처 중복 점검(회원 정보 수정) : 기존의 자신의 연락처를 배제한 나머지 연락처와 중복 점검
select count(*) from (
	select phone from member 
	where id != 'abcd1234'
) 
where phone = '010-9898-7878';

-- 회원 탈퇴 처리 => 삭제(X), 활성화 필드(useyn)=n(비활성화)
update member set useyn='n' where id='abcd12345';

-- 회원 탈퇴 처리 => 롤(Role) 게스트(guest) 하향화
update member_role set role='guest' where id='abcd12345';

-- 회원 활성화 여부 점검(활동/탈퇴 회원 점검)
select useyn from member where id='abcd12345';

-- 탈퇴 회원 정보 존재 여부
select count(*) from member
where id='abcd12345' 
  and pwd = '@Abcd12345'
  and email = 'jkjkjk@abcd.com'
  and phone = '010-5454-9898';
  
-- 개별 회원 롤(role) 정보 조회
select role from member_role where id='abcd12345';

-- 총 회원수
select count(*) from member; 