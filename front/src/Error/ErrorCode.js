import swal from "sweetalert";

function ErrorCode(result) {
  const code = result.code;

  if (code === 596) {
    swal(`${result.message}`, "", "error");
    return;
  }

  switch (code) {
    case 300:
      swal("올바르지 않은 경로입니다", "", "error");
      break;
    case 500:
      swal("내부 서버 에러", "", "error");
      break;
    case 501:
      swal("로그인을 해주세요", "", "error");
      break;
    case 502:
      swal("로그인 여부를 확인해주세요", "", "error");
      break;
    case 503:
      swal("회원이 아닙니다", "", "error");
      break;
    case 504:
      swal("없는 멤버입니다", "", "error");
      break;
    case 505:
      swal("이메일 키가 옳지 않습니다", "", "error");
      break;
    case 506:
      swal("이메일 키가 만료되었습니다", "", "error");
      break;
    case 509:
      swal("이미 다른 소셜로 회원 가입되어 있습니다", "", "error");
      break;
    case 510:
      swal("해당 그룹이 없습니다", "", "error");
      break;
    case 511:
      swal("올바른 카테고리가 아닙니다.", "", "error");
      break;
    case 512:
      swal("파일 업로드에 실패했습니다", "", "error");
      break;
    case 513:
      swal("패스워드가 틀렸습니다", "", "error");
      break;
    case 514:
      swal("중복된 이메일입니다", "", "error");
      break;
    case 515:
      swal("중복된 닉네임입니다", "", "error");
      break;
    case 516:
      swal("비밀번호 길이가 맞지 않습니다", "", "error");
      break;
    case 520:
      swal("관련 정보가 없습니다", "", "error");
      break;
    case 521:
      swal("올바른 값을 입력하세요", "", "error");
      break;
    case 522:
      swal("없는 방입니다", "", "error");
      break;
    case 523:
      swal("참가자가 아닙니다", "", "error");
      break;
    case 524:
      swal("같은 이름이 존재합니다", "", "error");
      break;
    case 525:
      swal("비밀번호가 숫자가 아닙니다", "", "error");
      break;
    case 526:
      swal("허용되지 않는 인원수입니다", "", "error");
      break;
    case 527:
      swal("허용되지 않는 종료기간입니다", "", "error");
      break;
    case 528:
      swal("이미지 파일이 아닙니다", "", "error");
      break;
    case 529:
      swal("이미지 파일의 크기는 10MB 이하여야 합니다", "", "error");
      break;
    case 530:
      swal("방장이 아닙니다", "", "error");
      break;
    case 531:
      swal("스터디 로그 생성을 실패했습니다", "", "error");
      break;
    case 532:
      swal(
        "스터디 참여시 비정상적으로 참여하여 시간이 저장되지 않았습니다.",
        "",
        "error"
      );
      break;
    case 533:
      swal("스프린트가 없습니다", "", "error");
      break;
    case 534:
      swal("이미 참가되었습니다", "", "error");
      break;
    case 535:
      swal("내가 참여한 그룹이 아닙니다", "", "error");
      break;
    case 536:
      swal("그룹장이 아닙니다", "", "error");
      break;
    case 537:
      swal("모집중인 스프린트가 아닙니다", "", "error");
      break;
    case 538:
      swal("포인트가 부족합니다", "", "error");
      break;
    case 540:
      swal("참가 신청하지 않았습니다", "", "error");
      break;
    case 541:
      swal("진행중인 스프린트가 아닙니다", "", "error");
      break;
    case 543:
      swal("스프린트가 이미 시작되었습니다", "", "error");
      break;
    case 544:
      swal("진행중인 스프린트 종료이후에 시작해야합니다", "", "error");
      break;
    case 545:
      swal("시작일자는 현재일자 이후여야 합니다", "", "error");
      break;
    case 546:
      swal("종료일자가 시작일자보다 빠릅니다", "", "error");
      break;
    case 547:
      swal("추방되어 스프린트에 참여할 수 없습니다", "", "error");
      break;
    case 548:
      swal("그룹원이 아니여서 볼 수 없습니다", "", "error");
      break;
    case 550:
      swal("인원 초과입니다", "", "error");
      break;
    case 551:
      swal("룸 비밀번호가 틀렸습니다", "", "error");
      break;
    case 552:
      swal("추방/반려되어 신청할 수 없습니다", "", "error");
      break;
    case 564:
      swal("비밀번호는 숫자만 가능합니다", "", "error");
      break;
    case 565:
      swal("허용되지 않습니다", "", "error");
      break;
    case 566:
      swal("이미 반려된 그룹입니다", "", "error");
      break;
    case 567:
      swal("이미 신청한 그룹입니다", "", "error");
      break;
    case 568:
      swal("그룹최대인원을 초과하였습니다", "", "error");
      break;
    case 569:
      swal("이미 정산된 스프린트 입니다", "", "error");
      break;
    case 570:
      swal("종료된 스프린트가 아닙니다", "", "error");
      break;
    case 571:
      swal("이미 삭제된 스프린트 입니다", "", "error");
      break;
    case 572:
      swal("이미 그룹에 가입되어있습니다", "", "error");
      break;
    case 573:
      swal("모집중인 스프린트가 있습니다", "", "error");
      break;
    case 574:
      swal("스프린트 방은 수정할 수 없습니다", "", "error");
      break;
    case 576:
      swal("강퇴되었습니다", "", "error");
      break;
    case 595:
      swal("비정상적인 접근입니다", "", "error");
      break;
    case 597:
      swal("카카오페이 오류입니다", "", "error");
      break;
    case 598:
      swal("충전금액은 0이상이어야 합니다", "", "error");
      break;
    case 599:
      swal("날짜 변환에 실패했습니다", "", "error");
      break;
  }
}

export default ErrorCode;
