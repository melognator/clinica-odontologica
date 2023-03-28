import React from 'react'
import { Outlet } from 'react-router-dom'
import Navbar from '../navbar/Navbar'

const Layout = () => {
  return (
    <>
        <Navbar />
        <section>
            <Outlet />
        </section>
    </>
  )
}

export default Layout