import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
//import com.tukorea.seottasitta.LoginFragment
import com.tukorea.seottasitta.R

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Firebase 인스턴스 초기화
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // 회원가입 버튼 클릭 시
        view.findViewById<View>(R.id.register_btn).setOnClickListener {
            registerUser(view)
        }

        return view
    }

    private fun registerUser(view: View) {
        val name = view.findViewById<TextInputEditText>(R.id.insert_name)?.text.toString().trim()
        val email = view.findViewById<TextInputEditText>(R.id.insert_id)?.text.toString().trim()
        val password = view.findViewById<TextInputEditText>(R.id.insert_pw)?.text.toString().trim()
        val confirmPassword =
            view.findViewById<TextInputEditText>(R.id.check_pw)?.text.toString().trim()

        // 이메일, 비밀번호 유효성 검사 등을 수행할 수 있습니다.

        // Firebase 회원가입
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // 회원가입 성공
                    val user = auth.currentUser
                    saveUserDataToFirestore(user?.uid, name, email)
                    Toast.makeText(
                        requireContext(),
                        "회원가입이 성공적으로 완료되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // 회원가입 후 필요한 작업 수행 (예: 다음 화면으로 이동)
                    // LoginFragment로 전환
                    val transaction: FragmentTransaction =
                        requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.login_btn, LoginFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else {
                    // 회원가입 실패
                    Toast.makeText(
                        requireContext(),
                        "회원가입 중 오류가 발생하였습니다. 다시 시도해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveUserDataToFirestore(userId: String?, name: String, email: String) {
        // Firebase Firestore에 사용자 데이터 저장
        userId?.let {
            val user = hashMapOf(
                "name" to name,
                "email" to email
            )

            firestore.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener {
                    // Firestore 저장 성공
                    // 추가로 수행할 작업이 있다면 여기에서 수행
                }
                .addOnFailureListener {
                    // Firestore 저장 실패
                    // 실패 시 수행할 작업이 있다면 여기에서 수행
                }
        }
    }
}
