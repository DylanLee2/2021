using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{

    public float moveSpeed = 10f;
    public float hitPoints = 100f;
    private Rigidbody2D rb;


    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody2D>();
        if(rb == null) {
            Debug.LogError("Player::Start cant find RigidBody2D </sadface>");
        }
    }

    // this is called at a fixed interval for use with physics objects like the RigidBody2D
    void FixedUpdate() {
        // check if user has pressed some input keys
        if (Input.GetAxisRaw("Horizontal") != 0 || Input.GetAxisRaw("Vertical") != 0) {

            // convert user input into world movement
            float horizontalMovement = Input.GetAxisRaw("Horizontal") * moveSpeed * Time.deltaTime;
            float verticalMovement = Input.GetAxisRaw("Vertical") * moveSpeed * Time.deltaTime;

            //assign world movements to a Veoctor2
            Vector2 directionOfMovement = new Vector2(horizontalMovement, verticalMovement);

            // apply movement to player's transform
            rb.AddForce(directionOfMovement);
        }
    }
/*
    // Update is called once per frame
    void Update()
    {   
        
        // float inputX = Input.GetAxis("Horizontal") * 5f * Time.deltaTime;
        // float inputY = Input.GetAxis("Vertical") * 5f * Time.deltaTime;
        // Vector3 movement = new Vector3(inputX, inputY, 0);
        // Player.transform.Translate(movement);

        if(Input.GetAxisRaw("Horizontal") != 0 || Input.GetAxisRaw("Vertical") != 0){
            float horizMovement = Input.GetAxisRaw("Horizontal") * moveSpeed * Time.deltaTime;
            float vertMovement = Input.GetAxisRaw("Vertical") * moveSpeed * Time.deltaTime;
            Vector3 direction = new Vector3(horizMovement, vertMovement, 0);
            gameObject.transform.Translate(direction);
            
        }
    }
*/

}
