# spring-gift-product

---
### 구현할 기능

- Product 클래스 생성 후 캡슐화
- ProductController에 RestController 어노테이션 적용 후 정보 저장용 Map구조 생성
- GetMapping어노테이션으로 메소드 생성 후 저장된 Product 객체들을 리스트형태로 반환하는 기능 구현
- PostMapping어노테이션으로 메소드 생성 후 RequestBody로 받은 JSON데이터를 Product객체로 만들고 해당 객체의 id를 1씩 증가시켜 리턴하는 기능 구현
- PutMapping어노테이션으로 메소드 생성 후 PathVariable에 해당하는 id의 데이터가 존재하는지 확인하고 전달받은 JSON데이터를 변환하여 객체 데이터를 수정하고 리턴하는 기능 구현
- DeleteMapping어노테이션으로 메소드 생성 후 PathVariable로 전달받은 id값에 해당하는 데이터가 존재하는지 확인 후 해당 데이터를 삭제하는 기능 구현