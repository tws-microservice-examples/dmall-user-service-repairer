package user

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name('should get a exist user')
            request {
                method GET()
                url ('/api/v1/users/1') {
                }
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": {
                                "contacts": "/api/v1/users/1/contacts",
                                "roles": [
                                    {
                                        "name": "经理"
                                    }
                                ], 
                                "name": "张三"
                            }
                        }
                    }
                """
            }
        },

        Contract.make {
            name('should get a exist user owned contacts')
            request {
                method GET()
                url ('/api/v1/users/1/contacts') {
                }
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": 1,
                            "attributes": [
                                {
                                    "name": "张三",
                                    "province": "北京市",
                                    "city": "北京",
                                    "area": "东城区",
                                    "street": "东直门内大街",
                                    "more_details": "国华投资大厦1105室"
                                }
                            ]
                        }
                    }
                """
            }
        },

        Contract.make {
            name('should post a new user success')
            request {
                method POST()
                url ('/api/v1/users') {
                }
                headers {
                    header 'Accept': 'application/json'
                    header 'Content-Type': 'application/json'
                }
                body """
                    {
                      "name": "李四",
                      "roles": [{"name": "员工"}]
                    }
                """
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": 2,
                            "attributes": {
                                "contacts": null,
                                "roles": [
                                    {
                                        "name": "员工"
                                    }
                                ],
                                "name": "李四"
                            }
                        }
                    }
                """
            }
        },

        Contract.make {
            name('should post a new contact success')
            request {
                method POST()
                url ('/api/v1/users/1/contacts') {
                }
                headers {
                    header 'Accept': 'application/json'
                    header 'Content-Type': 'application/json'
                }
                body """
                    {
                         "name": "李四",
                         "province": "北京市",
                         "city": "北京",
                         "area": "东城区",
                         "street": "东直门内大街",
                         "more_details": "国华投资大厦1105室",
                         "adress": "北京市北京东城区东直门内大街国华投资大厦1105室"
                    }
                """
            }
            response {
                status(200)
                headers {
                    header 'Content-Type': 'application/json;charset=UTF-8'
                }
                body """
                    {
                        "data": {
                            "id": -1,
                            "attributes": {
                                "name": "李四",
                                "province": "北京市",
                                "city": "北京",
                                "area": "东城区",
                                "street": "东直门内大街",
                                "more_details": "国华投资大厦1105室"
                            }
                        }
                    }
                """
            }
        },

]