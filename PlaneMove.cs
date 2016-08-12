using UnityEngine;
using System.Collections;

public class PlaneMove : MonoBehaviour {

    // Use this for initialization
    public static float finalTime = 0;
    public Vector3 triggerPose;
    public GameObject horseObj;
    public GameObject mainCam;
    public float speed,zOffset;
    private bool doneOnce = false;
    private bool doIt = false;
    private AudioSource audSor;
	void Start () {
        audSor = GetComponent<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {

        if ((HorseController.won && !HorseController.lost) && !doneOnce)
        {
            mainCam.GetComponent<AudioSource>().Stop();
            audSor.Play();
            transform.position = triggerPose;
            doneOnce = true; 
        }
        if (doIt)
        {
            
            // start the ascent
            transform.Translate(new Vector3(0f, Time.deltaTime * speed, 0f));
            horseObj.transform.position = transform.position + new Vector3(0f, 0f, zOffset);

        }
	
	}

    void OnTriggerEnter(Collider collider)
    {
        if (collider.gameObject.name.Equals("HorseObj"))
        {
            doIt = true;
        }
    }
}
