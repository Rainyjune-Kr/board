import { Container, Table } from "react-bootstrap";

function Board() {
  return (
    <div style={{justifyContent:'center'}}>
      <Container className="m-4">
      <Table className="table table-bordered table-hover">
        <thead>
          <tr>
            <th style={{width:'60px'}}>번호</th>
            <th style={{width:'400px'}}>제목</th>
            <th style={{width:'120px'}}>작성자</th>
            <th style={{width:'120px'}}>작성일</th>
            <th style={{width:'80px'}}>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td style={{}}>1</td>
            <td>테스트</td>
            <td>작성자</td>
            <td>2023.04.28</td>
            <td>1</td>
          </tr>
        </tbody>
      </Table>
      </Container>
    </div>
  )
}

export default Board