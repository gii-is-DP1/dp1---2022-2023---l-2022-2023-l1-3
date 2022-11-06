<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<!DOCTYPE html>
<html>
<head>
<style>
@import url("https://fonts.googleapis.com/css?family=VT323:400");
    .welcome {
    align-items: center;
    background-image: url(https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Color_negro.jpg/800px-Color_negro.jpg);
    background-position: 50% 50%;
    background-size: cover;
    border: none;
    display: flex;
    flex-direction: column;
    height: 100vh;
    justify-content: space-between;
    min-height: 842.16px;
    min-width: 1423.16px;
    padding: 190px 250px;
    width: 100%;
    }
    .hidden,
    .hidden * {
        pointer-events: none;
        visibility: hidden;
    
    }
    .button {
    display: inline-block;
    padding: 15px 25px;
    font-size: 24px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #272927;
    border: none;
    border-radius: 15px;
    box-shadow: 0 9px #999;
}

.button:hover {background-color: #0b0b0b}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
    .buttons {
    align-items: center;
    background-color: var(--licorice) ;
    border: 2px solid;
    border-color: var(--white) ;
    border-radius: 8px;
    display: flex;
    gap: 8px;
    height: 100px;
    justify-content: center;
    overflow: hidden;
    padding: 8px 16px;
    width: 500px;
    }
    
    .large {
        -webkit-text-stroke: 3px var(--white) ;
        letter-spacing: 0;
        line-height: 28px;
        white-space: nowrap;
        width: fit-content;
    }
    .valign-text-middle {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
        
    .title {
        -webkit-text-stroke: 3px var(--white) ;
        color: transparent;
        font-family: var(--font-family-vt323) ;
        font-size: var(--font-size-1) ;
        font-weight: 400;
        letter-spacing: 0;
        line-height: normal;
        margin-left: -2.92px;
        margin-top: -3.42px;
        transform: rotate(0.07deg) ;
        width: fit-content;
    }
    
    :root {
        --licorice: Mrgba(22, 19, 19, @.502);
        --white: Mrgba(255, 255, 255, 1);
        
        --font-size-m: 64px;
        --font-size-1: 128px;
        
        --font-family-vt323: "VT323";
    }
    .vt323-normal-licorice-64px {
        font-family: var(--font-family-vt323) ;
        font-size: var(--font-size-m) ;
        font-weight: 400;
        font-style: normal;
    }
</style>
</head>
  
<div class="welcome screen">
    <h1 class="title">Xtreme Parchis&Oca</h1>
    <div class="button">
    <div class="large valign-text-middle vt323-normal-
    licorice-64px">Iniciar sesion</div>
    <div class="button">
    <div class="large valign-text-middle vt323-normal-
    licorice-64px">Registrarme</div>

    </div>
</div>
</div>



    
