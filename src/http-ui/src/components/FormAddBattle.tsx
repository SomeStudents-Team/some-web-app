import React, {useState} from "react";
import { TextField, Button } from "@mui/material";
import BattleController from "../rest/controllers/BattleController";
import { v4 as uuid } from 'uuid';



const FormAddBattle = () => {
    const [title, setTitle] = useState<{value: string, error: boolean}>({
        value: "", error: false});

    const [description, setDesc] = useState<{value: string, error: boolean}>({
        value: "", error: false});

    const [name, setName] = useState<{value: string, error: boolean}>({
        value: "",
        error: false
    })

    const [firstMember, setFirstMember] = useState<{value: string, error: boolean}>({
        value: "",
        error: false
    })

    const [secondMember, setSecondMember] = useState<{value: string, error: boolean}>({
        value: "",
        error: false
    })

    const handleSubmit = (event: any) => {
        event.preventDefault()

        if(title.value === '')
            setTitle({value: "", error: true })


        if(description.value === '')
            setDesc({value: "", error: true })


        if(name.value === '')
            setName({value: "", error: true })


        if(firstMember.value === secondMember.value)
        {
            setFirstMember({value: firstMember.value, error: true});
            setSecondMember({value: secondMember.value, error: true});
        }

        if(firstMember.value === '')
            setFirstMember({value: "", error: true })


        if(secondMember.value === '')
            setSecondMember({value: "", error: true })

        if(!title.error && !description.error && !name.error && !firstMember.error && !secondMember.error)
            new BattleController().addBattle({
                title: title.value,
                description: description.value,
                owner: {
                    name: name.value,
                    ip: uuid()
                },

                members: [
                    {
                        name: firstMember.value,
                        score: 0,
                        image: ""
                    },
                    {
                        name: secondMember.value,
                        score: 0,
                        image: ""
                    }
                ]
            })
                .then(data => console.log(data.error))
    }

    return (
        <React.Fragment>
            <form autoComplete="off" onSubmit={handleSubmit}>
                <h2>Добавить новый баттл</h2>
                <TextField
                    label="Заголовок"
                    onChange={e => setTitle({
                        value: e.target.value,
                        error: false
                    })}
                    required
                    variant="outlined"
                    color="secondary"
                    type="text"
                    sx={{mb: 3}}
                    fullWidth
                    value={title.value}
                    error={title.error}
                />

                <TextField
                    label="Описание"
                    onChange={e => setDesc({
                        value: e.target.value,
                        error: false
                    })}
                    required
                    variant="outlined"
                    color="secondary"
                    type="text"
                    sx={{mb: 3}}
                    fullWidth
                    value={description.value}
                    error={description.error}
                />

                <TextField
                    label="Псевдоним"
                    onChange={e => setName({
                        value: e.target.value,
                        error: false
                    })}
                    required
                    variant="outlined"
                    color="secondary"
                    type="text"
                    sx={{mb: 3}}
                    fullWidth
                    value={name.value}
                    error={name.error}
                />

                <TextField
                    label="Первый соперник"
                    onChange={e => setFirstMember({
                        value: e.target.value,
                        error: false
                    })}
                    required
                    variant="outlined"
                    color="secondary"
                    type="text"
                    sx={{mb: 3}}
                    fullWidth
                    value={firstMember.value}
                    error={firstMember.error}
                />

                <TextField
                    label="Второй соперник"
                    onChange={e => setSecondMember({
                        value: e.target.value,
                        error: false
                    })}
                    required
                    variant="outlined"
                    color="secondary"
                    type="text"
                    sx={{mb: 3}}
                    fullWidth
                    value={secondMember.value}
                    error={secondMember.error}
                />

                <Button variant="outlined" color="secondary" type="submit">Добавить</Button>

            </form>
        </React.Fragment>
    );
}

export default FormAddBattle;