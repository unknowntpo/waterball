
import { SimpleChannel } from "channel-ts";

type WaterballBot = {
    eventCh?: MessageChannel,
}

type Message = {
    content: string
}

const createMessage = (content: string): Message => ({ content: content })

type MessageChannel = SimpleChannel<Message>

function createMessageChannel(): MessageChannel {
    return new SimpleChannel<Message>();
}

export const connect = async (bot: WaterballBot) => {
    console.log("connected to discord API")
    let eventCh = createMessageChannel()

    fireEvents(eventCh)

    await handleEvents(eventCh)
}

const handleEvents = async (ch: MessageChannel) => {
    for await (const msg of ch) { // use async iterator to receive data
        console.log(`Received: ${JSON.stringify(msg)}`);
    }
    console.log("Closed");
}

const fireEvents = async (ch: MessageChannel) => {
    let msgs: Message[] = [
        { content: "hello" },
        { content: "world" }
    ]
    msgs.forEach((msg) => ch.send(msg))
}


const delay = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));


export const createWaterballBot = () => ({

})


