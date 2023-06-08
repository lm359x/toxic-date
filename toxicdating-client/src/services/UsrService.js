import api, {USER_ID} from "../http"

export default class UsrService {
    async fetchUsers() {
        let users = [];
        await api.get("/search").then(
            res => {
                users = res.data;
            }
        )
        return users;
    }

    async fetchUser() {
        let user = null;
        await api.get(`/users/${USER_ID}`).then(
            res => user = res.data
        )
        return user;
    }

    async fetchChats() {
        let chats = null;
        await api.get(`/chats/filter/${USER_ID}`).then(
            res => chats = res.data
        )
        let usr_chat = []
        for (const chat of chats) {
            await api.get(`/chats/${chat.id}/participants`).then(
                res => {
                    for (const element of res.data) {
                        if (element.id !== USER_ID) {
                            usr_chat.push({
                                "usr": element,
                                "chat": chat
                            })
                        }
                    }
                }
            )
        }
        return usr_chat
    }

    async fetchChat(id) {
        let chat = null;
        await api.get(`/chats/${id}`).then(
            res => chat = res.data
        )
        let usr = null;
        await api.get(`/chats/${chat.id}/participants`).then(
            res => {
                for (const element of res.data) {
                    if (element.id !== USER_ID) {
                        usr = element;
                    }
                }
            }
        )
        return {usr,chat};
    }
}