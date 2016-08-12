using UnityEngine;
using System.Collections;

public class CameraController : MonoBehaviour {

    // Use this for initialization
    public string objCling;
    public Vector3 offset;
    private GameObject obj;
    
	void Start () {
        obj = GameObject.Find(objCling);
	}
	
	// Update is called once per frame
	void Update () {

        transform.position = obj.transform.position + offset;
        transform.LookAt(obj.transform);


    }
}
