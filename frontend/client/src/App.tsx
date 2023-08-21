import { useContext, useReducer, useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './style.scss'

import { createBrowserRouter, RouterProvider } from 'react-router-dom'


import axios from 'axios'
import { Layout } from './Components/Layout'
import { RegisterPage } from './Pages/RegisterPage'
import { LoginPage } from './Pages/LoginPage'
import { DepartmentPage } from './Pages/DepartmentsPage'
import { DepartmentDetail } from './Pages/DepartmentDetailPage'
import { SearchDepartmentPage } from './Pages/SearchUser'
import { HomePage } from './Pages/Home'
import { STDetail } from './Pages/STDetail'
import { ClientRequesDetailPage } from './Pages/ClientRequestDetailPage'
import { RequesDetailPage } from './Pages/RequestDetailPage'
import { CreateRequestPage } from './Pages/CreateRequestPage'
import { ChatIndexpage } from './Pages/ChatPages/Index'
import { ChatDetailPage } from './Pages/ChatPages/ChatDetail'
import { AllUsersPage } from './Pages/UserPages/AllUsersPage'
import { AllConnectionPage } from './Pages/UserPages/AllConnections'
import { ProfileDetailPage } from './Pages/UserPages/ProfileDetail'


//axios.defaults.baseURL = "http://localhost:8080/"

const routes = createBrowserRouter([
  {
    path: "",
    element: <Layout/>,
    children: [
      {
        path: "",
        element: <HomePage/>
      },
      {
        path: "/teste",
        element: "testzin"
      },

      // AUTH ROUTES
      {
        path: "/register",
        element: <RegisterPage/>
      },

      {
        path: "/login",
        element: <LoginPage/>
      },
      
      {
        path: "/departments",
        element: <DepartmentPage/>,
       
      },

      //Site Type Description
      {
        path: "/site/detail/:id",
        element: <STDetail/>
      },

      {
        path: "/departments/detail/:id",
        element: <DepartmentDetail/>
      },

      {
        path: "/departments/search",
        element: <SearchDepartmentPage/>
      },



      {
        path: "/departments/:dept_name/client_requests/:id",
        element: <ClientRequesDetailPage/>
      },
      {
        path: "/departments/:dept_name/requests/:id",
        element: <RequesDetailPage/>
      },
      {
        path: "/departments/:dept_id/request/new",
        element: <CreateRequestPage/>
      },


      {
        path: "/chat",
        element: <ChatIndexpage/>
      },

      {
        path: "/chat/detail/:profileId",
        element: <ChatDetailPage/>
      },
      {
        path: "/chat/add",
        element: <AllUsersPage/>
      },

      {
        path: "/chat/connections",
        element: <AllConnectionPage/>
      },

      {
        path: "/chat/connections/detail/:profileId",
        element: <ProfileDetailPage/>
      }

      
    ]
  }
])
      
/*const routes = createBrowserRouter([
  {
    path: "",
    element: <Layout/>,
    children:[
      {
        path:"",
        element:<Home/>
      },
      {
        path: "/add",
        element: <AddNewEmployee/>
      },
      {
        path: "/detail/:id",
        element: <DetailEmployee/>
      },{
        path: "/update/:id",
        element: <UpdateEmployee/>
      },{
        path: "/register",
        element: <Register/>
      }
    ]
  }
])*/

function App() {
  
  
  return(
   //<RouterProvider router={routes}/>
    <RouterProvider router={routes}/>
  )

  
}

export default App
