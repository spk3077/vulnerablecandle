// Object we save for userService.setLoggedUser()
export class SavedUser {
    constructor(
        public username: string,
        public authority: string,
        public picture: string
    ){}
}