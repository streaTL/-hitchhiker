
function regist() {
  let id = document.querySelector("#userId").value;
  let password = document.querySelector("#userPw").value;
  let email = document.querySelector("#userEmail").value;
  let name = document.querySelector("#userName").value;

  if (!id || !password || !name || !email) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    const user = {
      id: id,
      password: password,
      name: name,
      email: email,
    };

    localStorage.setItem("user", JSON.stringify(user));
    alert("사용자 등록 성공!");
    window.location.replace("login.html");
  }
}

function login() {
  let id = document.querySelector("#userId").value;
  let password = document.querySelector("#userPw").value;

  const user = JSON.parse(localStorage.getItem("user"));

  if (user.id && user.password && user.id === id && user.password === password) {
    alert("로그인 성공 !");
    localStorage.setItem("loginCheck", "1");
    window.location.replace("index.html");
  } else {
    alert("로그인 실패 !");
  }
}

function logout() {
  alert("로그아웃 성공!");
  localStorage.setItem("loginCheck", "0");
}
