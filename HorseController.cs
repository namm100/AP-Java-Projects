using UnityEngine;
using UnityEngine.UI;
using System.Collections;

[RequireComponent(typeof(Animator))]
public class HorseController : MonoBehaviour {

    //int runHash = Animator.StringToHash("Horse_Run");
    int walkHash = Animator.StringToHash("Horse_Walk");

    int idleHash = Animator.StringToHash("Horse_Idle");

    public static bool lost = false;
    public static bool won = false;
    
    public static int score = 0;
    private Animator anim;
    private AudioSource audSorce;

    public float xDevC, zDevC;
    public bool lostC, wonC;
    public static float timer;

    public Text timerText;
    public Text scoreText;
    public Text stateText;
    
    public float speed,rotateSpeed;
    public int scoreToWin;

	// Use this for initialization
	void Start () {
        anim = GetComponent<Animator>();
        audSorce = GetComponent<AudioSource>();
	}

    // Update is called once per frame
    void Update()
    {

        float vertical = Input.GetAxis("Vertical");
        float horizontal = Input.GetAxis("Horizontal");
        //float verticalDirection = vertical / Mathf.Abs(vertical);
        //float horiztonalDirection = horizontal / Mathf.Abs(horizontal);
        //handle animations...
        if (vertical != 0 || horizontal != 0)
        {
            anim.Play(walkHash);
        }
        else
        {
            anim.Play(idleHash);

        }
        // correction
        // transform.position = new Vector3(transform.position.x, 0f, transform.position.z);
        // keys
        Vector3 fwd = transform.InverseTransformDirection(transform.forward) * Time.deltaTime * speed; ;
        if (Input.GetKey(KeyCode.UpArrow))
        {
            transform.Translate(fwd);
        }
        if (Input.GetKey(KeyCode.DownArrow))
        {
            transform.Translate(-fwd);
        }
        if (Input.GetKey(KeyCode.RightArrow))
        {
            transform.Rotate(new Vector3(0f, rotateSpeed * Time.deltaTime, 0f));
        }
        if (Input.GetKey(KeyCode.LeftArrow))
        {
            transform.Rotate(new Vector3(0f, -rotateSpeed * Time.deltaTime, 0f));
        }
        if (Input.GetKey(KeyCode.J))
        {
            //Debug.Log(transform.forward * (speed * Time.deltaTime));
            Debug.Log("lost: " + lost + " Won: " + won);
        }
        /*
        if (Input.GetKey(KeyCode.Space))
        {
            transform.rotation = Quaternion.identity;
        }
        */

        float xDev = Mathf.Abs(transform.eulerAngles.x - 360);
        xDevC = xDev;
        float zDev = Mathf.Abs(transform.eulerAngles.y - 360);
        zDevC = zDev;
        if ((xDev > 30 && xDev < 340))
        {
            lost = true;
            stateText.text = "Sorry You Have LOST";
            //Application.Quit();
        }
        if (score >= scoreToWin && !lost)
        {
            won = true;
            stateText.text = "Fly To The Moon! You WON!";
        }
        lostC = lost;
        wonC = won;
        if (!won && !lost)
        {
            timer = Time.fixedTime;
        }
        
        timerText.text = "time: " + timer;
        scoreText.text = "score: " + score;
    }
    void OnTriggerEnter(Collider collider)
    {
        if (collider.name.Equals("Coin"))
        {
            score++;
            audSorce.Play();
            Destroy(collider.gameObject);
        }
        if (collider.name.Equals("Plane"))
        {
            transform.Rotate(50f, 0f, 0f);
        } 
    }
    void OnAnimatorMove()
    {

    }
}
