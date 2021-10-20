using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerMovement : MonoBehaviour
{

    public float moveSpeed = 10f;
    public float hitPoints = 100f;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        // Move the Character:
        transform.Translate(Input.GetAxis("Horizontal")* 15f * Time.deltaTime, 0f, 0f);

        // Flip the Character:
        Vector3 characterScale = transform.localScale;
        if (Input.GetAxis("Horizontal") < 0) {
            characterScale.x = -10;
        }
        if (Input.GetAxis("Vertical") > 0)
        {
            characterScale.y = 10;
        }
        transform.localScale = characterScale;
    }
}
