import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.tukorea.seottasitta.MainActivity
import com.tukorea.seottasitta.R

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Firebase 인스턴스 초기화
        auth = FirebaseAuth.getInstance()

        // 로그인 버튼 클릭 시
        view.findViewById<View>(R.id.login_btn).setOnClickListener {
            loginUser(view)
        }

        return view
    }

    private fun loginUser(view: View) {
        val email = view.findViewById<TextInputEditText>(R.id.login_email)?.text.toString().trim()
        val password =
            view.findViewById<TextInputEditText>(R.id.login_pw)?.text.toString().trim()

        // Firebase 로그인
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공
                    Toast.makeText(
                        requireContext(),
                        "로그인 성공",
                        Toast.LENGTH_SHORT
                    ).show()

                    // 로그인 성공 후 필요한 작업 수행 (예: 다음 화면으로 이동)
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    // 로그인 실패
                    Toast.makeText(
                        requireContext(),
                        "로그인 실패. 이메일과 비밀번호를 확인해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
