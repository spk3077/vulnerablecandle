// CLASS FOR RECEIVING TAG
export class TagReceive {
    constructor(
        public id: number,
        public name: string,
        public type: string
    ) {  }
}

// CLASS FOR SENDING TAG
export class TagSend {
    constructor(
        public name: string,
        public type: string
    ) {  }
}