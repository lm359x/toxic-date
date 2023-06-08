import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {createBrowserRouter} from "react-router-dom";
import Root from "./routes/root";
import {RouterProvider} from "react-router";
import ErrorPage from "./error-page";
import Settings, {loader as settingsLoader, action as settingsAction} from "./routes/settings";
import Search, {loader as searchLoader} from "./routes/search";
import Chats, {loader as chatsLoader} from "./routes/chats";
import Chat,{loader as chatLoader, action as chatAction} from "./routes/chat";




const router = createBrowserRouter([
    {
        path:"/",
        element:<Root/>,
        errorElement: <ErrorPage/>,
        children:[
            {
                path:"/settings",
                element:<Settings/>,
                loader:settingsLoader,
                action:settingsAction,
            },
            {
                path:"/search",
                element: <Search/>,
                loader: searchLoader
            },
            {
                path: "/chats",
                element: <Chats/>,
                loader: chatsLoader,
            },
            {
                path:"/chats/:id",
                element: <Chat/>,
                loader: chatLoader,
                action: chatAction
            }

        ]
    }
])


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <RouterProvider router={router}/>
);


