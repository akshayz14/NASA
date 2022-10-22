package com.example.nasaapp.action

interface ActionPerformer<ACTION> {
    fun performAction(action: ACTION)
}