package com.ramseys.jobplan.Data

object Constantes {
    const val BASE_URL = "http://192.168.43.215:8000/api/"
    //const val BASE_URL = "http://10.237.16.222:8000/api/"
    ///const val BASE_URL = "http://192.168.43.217:8000/api/"

    const val LOGIN_URL = "login"

    const val QUALIFICATION = "qualification"

    const val UNITE = "units"

    const val NATIONALITY = "nationalities"

    const val USERS = "users"

    const val USERSLISTS = "users/{id}"
    //recupere tableau de service selon le poste de travail
    const val WORKPLACE = "workplaces"

    //recupere la liste des pôstes de travail selon les unités
    const val WORKPLACELIST = "workplacesList/{id}"

    //recupere la liste des utilisateurs en fonction du poste de travail
    const val USERLIST = "userList"

    const val PLANNING = "plannings"

    const val PROGRAM = "postProgram"

    const val RECENTCHAT = "recent/{id}"

    const val CHAT = "chat/{idSender}/{idReceiver}"

    const val SEND = "chat"
    const val STATUSCHANGE = "statusChange/{matricule}"

    const val AFFECT ="affect"
}