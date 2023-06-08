import React from 'react'
import classes from './MessageContainer.module.css'
import Message from "./message/Message";

export default function MessageContainer(props){
    return(
        <div className={`${classes.chatMessageContainer} ${classes.scroll} container-fluid`}>
            {props.messages.length===0?
                <h5>Пока нет сообщений</h5>:
                props.messages.map(m=>
                    (<Message message={m} usr={props.usr} key={m.id}/>)
                )
            }
        </div>
    )
}